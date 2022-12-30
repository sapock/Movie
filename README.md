# Movie 예매

본 예제는 MSA/DDD/Event Storming/EDA 를 포괄하는 분석/설계/구현/운영 전단계를 커버하도록 구성한 예제입니다. 이는 클라우드 네이티브 애플리케이션의 개발에 요구되는 체크포인트들을 통과하기 위한 예시 답안을 포함합니다.



## 서비스 시나리오
### 영화관 관리자
1. 관리자가 해당 영화관에 상영 중인 영화 정보를 등록한다.(제목,줄거리,평균 평점)
3. 관리자가 등록된 영화 정보를 삭제한다.
4. 관리자가 영화의 상영 정보를 등록한다
5. 관리자가 상영 정보(상영 시간)을 수정한다. 상영 시간 정보가 바뀌면 고객에게 알려준다.

### 고객
1. 고객이 영화를 예매한다 - 잔여 좌석이 있는 경우만 예매 가능
4. 고객이 영화 예매 내역을 결제한다
5. 고객은 영화 예매 내역을 취소할 수 있다 - 영화 시작 전에만 취소 가능하며, 취소가 완료되면 결제도 취소 처리된다
7. 고객이 예매 내역 정보를 조회한다.
8. 고객은 예매한 영화 관람 이후 영화 평점과 후기를 등록할 수 있다.
9. 고객은 영화 평점을 조회할 수 있다.
10. 고객은 본인이 등록한 영화 평점을 삭제할 수 있다.

### 결제시스템
1. 결제요청이 들어오면 결제 승인한다.
2. 결제취소 요청이 들어오면 결제 취소한다.


### 비기능적 요구사항
1. 예약이 완료된 건은 즉시 결제 처리(승인) 되어야 한다 (Sync 호출)

### 장애격리
1. 결제시스템이 비정상 상태여도, 예약 취소는 가능하다.
2. 평점 관리 시스템 Circuit  breaker, fallback 

### 성능
1. 고객은 본인의 예매 내역을 확인할 수 있어야 한다 (CQRS)
2. 고객은 영화의 평점 정보를 확인할 수 있어야 한다 (CQRS)



## 체크포인트

## 분석/설계

![image](https://user-images.githubusercontent.com/49747084/209963026-96c742a1-2a77-45a2-994d-388f366345d5.png)


## 구현


### 서킷브레이킹/장애격리

- Spring FeignClient + Hystrix 옵션을 사용하여 구현함

- application.yml Hystrix 를 설정
<pre><code>
feign:
  hystrix:
    enabled: true
    
hystrix:
  command:
    default:
      execution.isolation.thread.timeoutInMilliseconds: 610
</code></pre> 

- Rating.java의 @PrePersist 로
reservation 서비스를 호출한 결과
reservation 결과가 없을 경우 오류를 발생시킴

<pre><code>
@PrePersist
    public void onPrePersist() {
        // get from reservation
        Reservation reservation = 
        RatingApplication.applicationContext.getBean(ReservationService.class)
        .getReservation(Long.valueOf(getReserveId()));
        
        if(reservation == null ){
            throw new RuntimeException("Unavailable!!!");
     } 
 }
 </code></pre>
 
 - 수행 결과
 <pre><code>
 {결과화면}
 </code></pre>

### Gateway/Ingress
- gateway 스프링부트 app 추가
- application.yaml내에 rating, screen, dashboard 등 각 마이크로 서비스의 routes 를 추가하고 gateway 서버의 포트 8080 설정
- Kubernetes에 생성된 Deploy. 확인

![image](https://user-images.githubusercontent.com/117143880/210031686-33985e23-4158-4ad5-82be-45a722f613bb.png)

- Ingress를 통한 진입점 통일

![image](https://user-images.githubusercontent.com/117143880/210032858-20741d7b-1d1a-47d6-9547-8c1b921ef763.png)

- Kubernetes용 Service.yaml을 작성하고 Kubernetes에 Service/LoadBalancer을 생성하여 엔드포인트를 확인.

![image](https://user-images.githubusercontent.com/117143880/210031800-dbaa3153-e087-4e8e-a652-857cb4e40510.png)


### Deploy/Pipeline
-public 주소로 접속 가능

![image](https://user-images.githubusercontent.com/117143880/210031960-56073ac2-f484-4805-abfd-b979a7dfbfba.png)


### Autoscale(HPA)
- 예매서비스에 대한 replica 를 동적으로 늘려주도록 HPA 를 설정한다. 설정은 CPU 사용량이 50프로를 넘어서면 replica 를 3개까지 늘려준다:
<pre><code>,,,
kubectl autoscale deployment reservation --cpu-percent=50 --min=1 --max=3
,,,
</code></pre>
- 적용화면

![image](https://user-images.githubusercontent.com/117143880/210032616-e95eee4c-c136-4a2e-b327-13b5d1c6531a.png)


### Zero-downtime deploy (Readiness probe)
- deployment.yaml 파일에 readinessProbe 설정

![image](https://user-images.githubusercontent.com/117251587/210034003-7b99130a-7ee3-4a71-bcfc-d6866c511eff.JPG)


- siege pod 을 통해 무정지 배포가 되는지 확인

![image](https://user-images.githubusercontent.com/117251587/210034009-78859fe1-4970-4a8f-8f35-87742f180767.JPG)

![image](https://user-images.githubusercontent.com/117251587/210034371-2065a5ab-90c8-4828-a482-2aa0e845ce08.png)
