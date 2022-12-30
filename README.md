# Movie 예매

본 예제는 MSA/DDD/Event Storming/EDA 를 포괄하는 분석/설계/구현/운영 전단계를 커버하도록 구성한 예제입니다. 이는 클라우드 네이티브 애플리케이션의 개발에 요구되는 체크포인트들을 통과하기 위한 예시 답안을 포함합니다.



## 서비스 시나리오
1. 관리자가 해당 영화관에 상영 중인 영화 정보를 등록한다.(제목,줄거리,평균 평점)
3. 관리자가 등록된 영화 정보를 삭제한다.
4. 관리자가 영화의 상영 정보를 등록한다
5. 관리자가 상영 정보(상영 시간)을 수정한다. 상영 시간 정보가 바뀌면 고객에게 알려준다.
1. 고객이 영화를 예매한다 - 잔여 좌석이 있는 경우만 예매 가능
4. 고객이 영화 예매 내역을 결제한다
5. 고객은 영화 예매 내역을 취소할 수 있다 - 영화 시작 전에만 취소 가능하며, 취소가 완료되면 결제도 취소 처리된다
7. 고객이 예매 내역 정보를 조회한다.
8. 고객은 예매한 영화 관람 이후 영화 평점과 후기를 등록할 수 있다.
9. 고객은 영화 평점을 조회할 수 있다.
10. 고객은 본인이 등록한 영화 평점을 삭제할 수 있다.
(결제시스템)
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
### 점검방법 : Movie_HTTP.txt 파일의 http request 목록으로 실행 점검

### Saga (Pub-Sub)
- 잔여좌석처리, 평점평균처리등에 Saga 패턴을 적용함
- 평점을 등록하면, 영화정보에 평점평균으 업데이트 하는 케이스
![image](https://user-images.githubusercontent.com/117365912/210039670-89cdb013-4185-450f-a696-1b0b93d8f166.png)
![image](https://user-images.githubusercontent.com/117365912/210042177-64dbe940-dc0c-4ee3-9b4b-c0673c1e66ea.png)

### CQRS
- 예약정보 및 평점정보 dashboard 구현
![image](https://user-images.githubusercontent.com/117365912/210039618-945de9e4-1659-49b8-9d45-de45b08643ab.png)

### Compensation & Correlation
- 4명 예약 시, 잔여좌석 4가 차감됨(120->116)
![image](https://user-images.githubusercontent.com/117365912/210041976-74136adc-77e5-41dd-bdaf-92fda02dbc3e.png)

- 예약 취소 시, 잔여좌석 4가 증가함(116->120)
![image](https://user-images.githubusercontent.com/117365912/210041807-fcc707dc-e3d7-45c7-a64b-74d9bd34b15d.png)
![image](https://user-images.githubusercontent.com/117365912/210041840-a57332dd-cf0c-4e3d-b954-4a327e05965d.png)


### Request-Response
- 결재승인은 실시간 처리를 위해, req-res로 구현함
![image](https://user-images.githubusercontent.com/117365912/210040220-3aa8d36b-2d6a-4c5f-90d9-105d1e3f9e8c.png)



### Circuit Breaker
- Spring FeignClient + Hystrix 옵션을 사용하여 구현함
- application.yml Hystrix 를 설정

  ![image](https://user-images.githubusercontent.com/117143880/210035409-182c3ea2-533a-4246-a04e-23b96ff1281a.png)

- Rating.java의 @PrePersist 로
reservation 서비스를 호출한 결과
reservation 결과가 없을 경우 오류를 발생시킴

  ![image](https://user-images.githubusercontent.com/117143880/210035244-d763ac80-4350-434b-88e1-e4b8f08a3990.png)

 - 수행 결과
   서킷브레이커 작동 로그 확인
 <pre><code>
 java.lang.RuntimeException: Hystrix circuit short-circuited and is OPEN
 </code></pre>
 
 - fallback 처리 (장애 발생 시에 대체 활동)
   reservation 서비스가 중지된 상태에서 리뷰를 등록하고자 할 경우
 - rating/.../ReservationService.java FeignClient에 fallback 옵션을 준다.
 
   ![image](https://user-images.githubusercontent.com/117143880/210034593-be5ee7a0-3fa2-48cf-8575-0bf38cf57691.png)
 - fallback 구현체 구현
   rating/.../ReservationServiceImpl.java
   
   ![image](https://user-images.githubusercontent.com/117143880/210034905-08d3dbc5-530b-408f-b414-234dc2f9526a.png)
 - 장애 발생으로 reservation 서비스를 호출하지 못했을 경우, ReservationServiceImpl 의 getReservation 메서드를 실행하여 대체값을 리턴한다.
 
   ![image](https://user-images.githubusercontent.com/117143880/210039838-4a56ba9c-5b03-48a0-bb27-cf00b93ee87b.png)

### Gateway/Ingress
- gateway 스프링부트 app 추가
- application.yaml내에 rating, screen, dashboard 등 각 마이크로 서비스의 routes 를 추가하고 gateway 서버의 포트 8080 설정
- Kubernetes에 생성된 Deploy. 확인

  ![image](https://user-images.githubusercontent.com/117143880/210031686-33985e23-4158-4ad5-82be-45a722f613bb.png)

- Kubernetes용 Service.yaml을 작성하고 Kubernetes에 Service/LoadBalancer을 생성하여 엔드포인트를 확인.

  ![image](https://user-images.githubusercontent.com/117143880/210031800-dbaa3153-e087-4e8e-a652-857cb4e40510.png)


### Deploy/Pipeline
- public 주소로 접속 가능

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


### Persistence Volume
 1. EFS 생성
  ![image](https://user-images.githubusercontent.com/49747084/210037806-42f1c94e-abe9-45c4-9e5c-c124c5e1bb21.png)
  - Network 에서 각 AZ별 Security groups 추가
  ![image](https://user-images.githubusercontent.com/49747084/210037842-2ad25cc6-30d4-43bd-8d33-5591d5fb1627.png)

 2. ServiceAccount 생성 
 - default namespace 사용
  ![image](https://user-images.githubusercontent.com/121626006/210042218-a28fec1b-3035-46e3-9ecf-6055ca58be4e.png)

  ![image](https://user-images.githubusercontent.com/49747084/210038010-7421b7cb-f3d4-4ef2-b63a-379d33befcad.png)

 3. 서비스 계정(efs-provisioner)에 권한(rbac) 설정
  ![image](https://user-images.githubusercontent.com/49747084/210038122-86a71c9e-e04d-452f-9b7d-e5ab9dd5a325.png)

 4.  EKS에 EFS Provisioner 설치
  ![image](https://user-images.githubusercontent.com/49747084/210038907-609bf464-a8e4-4ac4-9894-7bc11582331a.png)
  
  ![image](https://user-images.githubusercontent.com/49747084/210038972-9a73574d-1f4b-4de8-ae9e-33459de50ec9.png)
  
  - 생성된 Provisioner 를 StorageClass 에 등록 후 확인
   ![image](https://user-images.githubusercontent.com/49747084/210039004-ca1d25c4-ad74-4217-9e73-4e223acb3b05.png)
   
   ![image](https://user-images.githubusercontent.com/49747084/210039091-4b70c70c-fe5b-4f68-a5e6-225af00f5925.png)

 5. 해당 Provisioner 사용하는 pvc 생성 후 pvc 조회하여 확인
  ![image](https://user-images.githubusercontent.com/121626006/210041858-51bd876d-9955-44b6-8b0b-36345addcbd1.png)
 
 6. deployment.yaml 에 volume 정보 등록 
  ![image](https://user-images.githubusercontent.com/49747084/210039235-a78b8c67-fdbb-4a11-9bb0-4bf612b76c79.png)
  
  ![image](https://user-images.githubusercontent.com/49747084/210039277-f20fcaee-df2e-4222-8603-ddfd44135b04.png)

 7. 배포 후 해당 pod 에 파일시스템이 정상 마운트 되는 것 확인
  ![image](https://user-images.githubusercontent.com/49747084/210039374-6a976534-e3f4-49fe-9408-a35351ccd422.png)




### Self-healing (liveness probe)
- deployment.yml 파일 수정

  ![image](https://user-images.githubusercontent.com/117143880/210034409-a88f8aa7-f52c-4d72-b24a-320c002ed4b8.png)


