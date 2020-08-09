create SEQUENCE seq_board;

CREATE TABLE tbl_board (
    bno number(10,0),
    title nvarchar2(200) not null,
    content NVARCHAR2(2000) not null,
    writer NVARCHAR2(50) not null,
    regdate Date default sysdate,
    updatedate date default sysdate
);

alter table tbl_board add CONSTRAINT pk_board PRIMARY KEY(bno);

INSERT INTO tbl_board (bno, title, content, writer) VALUES (
    SEQ_BOARD.nextval, '테스트 제목', '테스트 내용', 'user00'
);

COMMIT;

select * from tbl_board;