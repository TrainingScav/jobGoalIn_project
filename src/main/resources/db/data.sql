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


INSERT INTO community_post_info (inst_date, inst_id, title, content) VALUES
('2024-01-01', 'korea_dev', '신년 인사드립니다', '2024년에도 커뮤니티와 함께 성장해요!'),
('2024-01-02', 'frontend_fox', '프론트엔드 기술 공유', '최근 트렌드인 React 서버 컴포넌트 이야기입니다.'),
('2024-01-03', 'backend_bear', 'SpringBoot 팁', 'JPA를 활용한 데이터 모델링 전략 정리'),
('2024-01-04', 'data_girl', '데이터 분석 입문기', 'SQL 쿼리부터 시각화까지 내가 배운 것들'),
('2024-01-05', 'design_ocean', '디자인 패턴 정복하기', 'UI/UX 설계 원칙과 실전 노하우'),
('2024-01-06', 'cloud_rider', 'AWS EC2 설정 가이드', '처음부터 배포까지 따라해보는 튜토리얼'),
('2024-01-07', 'java_master', 'Java 기초 정리 노트', '이해 안되던 개념들이 싹 정리됨'),
('2024-01-08', 'html_cat', 'HTML5 꿀팁', '폼 태그와 유효성 체크까지 한 번에!'),
('2024-01-09', 'css_wave', '애니메이션 효과 적용법', 'CSS로 만드는 자연스러운 효과들'),
('2024-01-10', 'vue_moon', 'Vue.js 3 트릭 모음', 'Composition API 활용 노하우'),
('2024-01-11', 'node_rocket', 'Node.js로 API 만들기', 'RESTful 설계와 Express 사용법'),
('2024-01-12', 'ai_vision', 'AI 프로젝트 후기', 'TensorFlow로 만든 이미지 분류기 썰'),
('2024-01-13', 'security_knight', '보안 모범 사례', 'SQL Injection 방어 방법과 로그 관리'),
('2024-01-14', 'mentor_tree', '커뮤니티 멘토링 제안', '초보자 대상 멘토링 프로그램을 제안합니다'),
('2024-01-15', 'mobile_hands', '모바일 앱 제작기', 'Flutter로 만든 커뮤니티 앱 후기'),
('2024-01-16', 'infra_lion', '서버 인프라 구성 전략', '웹 서버 + DB 서버 클러스터 구축기'),
('2024-01-17', 'tech_writer', '기술 블로그 시작하기', '나만의 기록 공간 만들기'),
('2024-01-18', 'jobseek_hope', '취준생 응원 글', '면접 준비하면서 힘든 점 공유합니다'),
('2024-01-19', 'career_change', '이직 후기', '디자이너에서 개발자로 전향한 이야기'),
('2024-01-20', 'student_dev', '학부생 개발자 성장일지', '졸업작품 만들면서 깨달은 점들');