-- 멤버 1명
insert into member (username, password, email, role, social_type, social_id, created_at, updated_at) values
('테스트한글', 'test1234', 'test@gmail.com', 'ROLE_USER', 'google', 'google_id', now(), now());

-- 주제 3개
insert into subject (subject_name, created_at, updated_at) values
('subject1', now(), now()),
('subject2', now(), now()),
('subject3', now(), now());

-- 주제1 1개, 주제2 2개, 주제3 3개
-- 질문 6개
insert into question (subject_id, content, created_at, updated_at) values
(1, 'question1', now(), now()),
(2, 'question2', now(), now()),
(2, 'question3', now(), now()),
(3, 'question4', now(), now()),
(3, 'question5', now(), now()),
(3, 'question6', now(), now());

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