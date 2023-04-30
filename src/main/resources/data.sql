-- 멤버 1명
insert into member (username, email, role, social_type, social_id, created_at, updated_at) values
('테스트한글', 'test@gmail.com', 'ROLE_USER', 'google', 'google_id', now(), now());

-- 운영체제         : operating_system , subject_id = 1
-- 데이터베이스      : database_system , subject_id = 2
-- 컴퓨터구조론      : computer_architecture , subject_id = 3
-- 자료구조         : data_structure , subject_id = 4
-- 알고리즘         : algorithm      , subject_id = 5
-- 컴퓨터네트워크     : computer_network , subject_id = 6

-- 주제 6개
insert into subject (subject_name, created_at, updated_at) values
('operating_system', now(), now()),
('database_system', now(), now()),
('computer_architecture', now(), now()),
('data_structure', now(), now()),
('algorithm', now(), now()),
('computer_network', now(), now());

-- 각 질문 10개씩 메타데이터

-- 운영체제 , subject_id : 1
insert into question (subject_id, content, created_at, updated_at) values
(1, '프로세스에 대해 설명해 주세요.', now(), now()),
(1, '쓰레드에 대해 설명해 주세요.', now(), now()),
(1, 'IPC(Inter-Process-Communication)에 대해 설명해 주세요.', now(), now()),
(1, '교착 상태(DeadLock)에 대해 설명해 주세요.', now(), now()),
(1, '세마포어에 대해 설명해 주세요.', now(), now()),
(1, '뮤텍스에 대해 설명해 주세요.', now(), now()),
(1, '페이지 교체 알고리즘 중, LRU(Least-Recently-Used) 알고리즘에 대해 설명해 주세요.', now(), now()),
(1, '캐시 메모리에 대해 설명해 주세요.', now(), now()),
(1, '인터럽트에 대해 설명해 주세요.', now(), now()),
(1, 'Context-Switch에 대해 설명해 주세요.', now(), now());

-- 데이터베이스 , subject_id : 2
insert into question (subject_id, content, created_at, updated_at) values
(2, '관계형 데이터베이스의 장점에 대해 설명해 주세요.', now(), now()),
(2, '비관계형 데이터베이스의 장점에 대해 설명해 주세요.', now(), now()),
(2, 'Index에 대해 설명해 주세요.', now(), now()),
(2, '트랜잭션이란 무엇인가요?', now(), now()),
(2, '트랜잭션의 4가지 특징에 대해 설명해 주세요.', now(), now()),
(2, '정규화의 목적에 대해 설명해 주세요.', now(), now()),
(2, 'Primary Key에 대해 설명해 주세요.', now(), now()),
(2, 'Join에 대해 설명해 주세요.', now(), now()),
(2, 'Delete,Truncate,Drop의 차이점은 무엇인가요?', now(), now()),
(2, '데이터베이스의 이상(Anomaly) 현상이란 무엇인가요?', now(), now());

-- 컴퓨터구조론 , subject_id : 3
insert into question (subject_id, content, created_at, updated_at) values
(3, 'CPU의 동작 과정에 대해 설명해 주세요.', now(), now()),
(3, 'Direct Mapped Cache의 장점 및 단점에 대해 설명해 주세요.', now(), now()),
(3, 'Fully Associative Cache란 무엇인가요?', now(), now()),
(3, '고정 소수점 표현 방식이란 무엇인가요?', now(), now()),
(3, '부동 소수점 표현 방식이란 무엇인가요?', now(), now()),
(3, '캐시의 지역성 원리에 대해 설명해 주세요.', now(), now()),
(3, '시스템 소프트웨어와 응용 소프트웨어의 차이점은 무엇인가요?', now(), now()),
(3, '파이프라이닝이란 무엇인가요?', now(), now()),
(3, '메인 메모리의 heap 영역에 대해 설명해 주세요.', now(), now()),
(3, '패리티 비트에 대해 설명해 주세요.', now(), now());

-- 자료구조 , subject_id : 4
insert into question (subject_id, content, created_at, updated_at) values
(4, '힙 자료구조란 무엇인가요?', now(), now()),
(4, '트리의 순회 방식에 대해 설명해 주세요.', now(), now()),
(4, '링크드리스트의 단점에 대해 설명해 주세요.', now(), now()),
(4, '해시의 충돌 문제를 해결하기 위한 방안 중에는 어떤 것들이 존재하나요?', now(), now()),
(4, '웹 브라우저의 이전페이지로 돌아가기와 에디터의 되돌리기 Undo 기능을 구현하기에 적합한 자료구조는 무엇인가요?', now(), now()),
(4, '그래프의 인접 리스트 구조에 대해 설명해 주세요.', now(), now()),
(4, '그래프의 인접 행렬 구조에 대해 설명해 주세요.', now(), now()),
(4, '이진 탐색 트리란 무엇인가요?', now(), now()),
(4, 'AVL 트리란 무엇인가요?', now(), now()),
(4, 'Red-Black-Tree란 무엇인가요?', now(), now());

-- 알고리즘 , subject_id : 5
insert into question (subject_id, content, created_at, updated_at) values
(5, '동적 계획법(Dynamic Programming)에 대해 설명해 주세요.', now(), now()),
(5, '힙 정렬의 시간복잡도는 무엇인가요?', now(), now()),
(5, '깊이 우선 탐색에 대해 설명해 주세요.', now(), now()),
(5, '너비 우선 탐색에 대해 설명해 주세요.', now(), now()),
(5, 'Stable Sort란 무엇인가요?', now(), now()),
(5, '다익스트라 알고리즘에 대해 설명해 주세요.', now(), now()),
(5, '피보나치 수열을 구현할 수 있는 알고리즘 3가지를 설명하고, 각 시간복잡도에 대해 설명해 주세요.', now(), now()),
(5, '퀵 정렬의 경우 최악의 시간 복잡도 N^2이 발생하는 경우를 설명해 주세요.', now(), now()),
(5, 'bitmask를 사용하는 경우에 대해 설명해 주세요.', now(), now()),
(5, '분할-정복 기법을 사용하는 알고리즘 3가지는 무엇인가요?', now(), now());

-- 컴퓨터네트워크 , subject_id : 6
insert into question (subject_id, content, created_at, updated_at) values
(6, '3way handshaking에 대해 설명해 주세요.', now(), now()),
(6, '4way handshaking에 대해 설명해 주세요.', now(), now()),
(6, 'OSI 7계층에 대해 설명해 주세요.', now(), now()),
(6, 'TCP와 UDP의 차이점은 무엇인가요?', now(), now()),
(6, 'HTTP란 무엇인가요?', now(), now()),
(6, 'TCP/IP의 흐름제어에 대해 설명해 주세요.', now(), now()),
(6, 'TCP/IP의 혼잡제어에 대해 설명해 주세요.', now(), now()),
(6, '쿠키와 세션의 차이점에 대해 설명해 주세요.', now(), now()),
(6, 'GET과 POST의 차이점에 대해 설명해 주세요.', now(), now()),
(6, 'PUT과 PATCH의 차이점에 대해 설명해 주세요.', now(), now());

-- 채팅방 2개
insert into chat_room (member_id, subject_id, title, created_at, updated_at) values
(1, 1, 'chatroom1', now(), now()),
(1, 2, 'chatroom2', now(), now());

-- 봇 메시지 1개 (북마크 되어있음)
insert into bot_message (chat_room_id, content, is_bookmarked, created_at, updated_at) values
(1, 'bot-message1', true, now(), now());

-- 유저 메시지 1개 (북마크 안되어 있음)
insert into user_message (chat_room_id, content, is_bookmarked, created_at, updated_at) values
(1, 'user-message1', false, now(), now());