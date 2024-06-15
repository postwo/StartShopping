distributionBase=GRADLE_USER_HOME
distributionPath=wrapper/dists
distributionUrl=https\://services.gradle.org/distributions/gradle-8.1.1-bin.zip
networkTimeout=10000
validateDistributionUrl=true
zipStoreBase=GRADLE_USER_HOME
zipStorePath=wrapper/dists


이걸로 해야 빌드가 된다 버전이 이게 맞다

![test코드하는데 계속 gradle오류 뜨는이유.PNG](..%2F..%2FUsers%2F%EC%B5%9C%EC%B9%98%EC%96%B8%2FOneDrive%2F%EC%82%AC%EC%A7%84%2F%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%2Ftest%EC%BD%94%EB%93%9C%ED%95%98%EB%8A%94%EB%8D%B0%20%EA%B3%84%EC%86%8D%20gradle%EC%98%A4%EB%A5%98%20%EB%9C%A8%EB%8A%94%EC%9D%B4%EC%9C%A0.PNG)

intellij로 변경안해서 test코드중 계속 에러뜨는거였다



<link rel="stylesheet" href="/css/regiestcss/regietst.css"> css넣는방법


오류
비밀번호 재확인 일치 하지 않으면 문자 띄워주기가 안됨

1.querydsl생성방법(querydsl은 jpql(=nativequery)보다 상위버전이라고 생각하면 된다)
gradle에다가 의존성을 다주입후 
gradle에 들어가서 other 에서 compilejava를 누르면 q(entity)가 생성된다
![querydsl생성방법1.PNG](..%2F..%2FUsers%2F%EC%B5%9C%EC%B9%98%EC%96%B8%2FOneDrive%2F%EC%82%AC%EC%A7%84%2F%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%2Fquerydsl%EC%83%9D%EC%84%B1%EB%B0%A9%EB%B2%951.PNG)

![Q클래스 생성.PNG](..%2F..%2FUsers%2F%EC%B5%9C%EC%B9%98%EC%96%B8%2FOneDrive%2F%EC%82%AC%EC%A7%84%2F%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%2FQ%ED%81%B4%EB%9E%98%EC%8A%A4%20%EC%83%9D%EC%84%B1.PNG)



querydsl 생성하고 이러한 에러가 뜨면(Qentity 재생성 오류)
java: Attempt to recreate a file for type com.example.startshopping.entity.QOauth2Entity
