## Java로  .sql 파일 만들기

서울시 병의원 정보가 들어있는 .csv 파일을 읽어서 `seoul_hospital_insert.sql`을 작성하는 코드를 만들자.

1. 파일 만들기
2. .csv에서 line별로 읽어오기

   📄`class LineReader`
   파일 생성하고 라인 읽어오는 클래스

   📄[Parser] `interface Parser`
   갈아끼우는 조립부분 역할

   📄[Parser] `class HospitalParser`
   hospital을 파싱하는 파서

3. 📄`Hospital()` class 만들기
4. line 별로 읽어와서 필요한 정보만 뽑아서 Object에 담기
5. .sql 파일 생성하기
6. 파일에 Object 내용 sql 포맷으로 .sql 파일에 입력하기