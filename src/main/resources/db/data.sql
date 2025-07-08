INSERT INTO recruit_info (
    company_id, title, company_name, content, require_career_years, employment_type,
    location, inst_id, posted_at, deadline, inst_date
) VALUES
(101, 'Java 백엔드 개발자', 'TechNova','Spring Boot 기반의 웹 서비스 백엔드 개발을 담당합니다.', '3년이상','정규직', '서울 강남구', 1001, '2025-06-28', '2025-07-15', '2025-06-28'),
(102, '프론트엔드 개발자 (React)', 'GreenFoods','SPA 기반의 프론트엔드 개발 및 퍼포먼스 최적화를 담당합니다.','1년이상','계약직', '성남시 분당구', 1002, '2025-06-26', '2025-07-10', '2025-06-25'),
(103, 'DevOps 엔지니어', 'SkyMedia','CI/CD 환경 구성 및 클라우드 인프라 관리 업무를 수행합니다.','5년이상','정규직', '서울 종로구', 1003, '2025-06-24', '2025-07-05', '2025-06-23'),
(104, '데이터 분석가', 'BioHealth','비즈니스 인사이트 도출을 위한 데이터 분석 및 시각화를 담당합니다.', '경력무관','인턴', '서울 마포구', 1004, '2025-06-27', '2025-07-20', '2025-06-26'),
(105, 'AI 엔지니어','Samsung','LLM 기반 AI 모델 학습 및 서비스 연동을 담당합니다.', '2년이상','정규직', '부산 해운대구', 1005, '2025-06-30', '2025-07-25', '2025-06-30');


INSERT INTO resume_list (user_id, title, content, is_experienced, is_show, inst_id)
VALUES
(1, '백엔드 개발자 이력서', 'Java, Spring Boot (경력 3년)', 'Y', 'Y', 'user01'),
(2, '프론트엔드 신입 이력서', 'HTML, CSS, React 학습 완료 (경력 1년)', 'N', 'N', 'user02'),
(3, '데이터 분석가 경력직', 'Python, SQL, Pandas 사용 경험 있음 (경력 5년)', 'Y', 'Y', 'user03');
