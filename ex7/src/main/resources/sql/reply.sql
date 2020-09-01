CREATE TABLE tbl_reply(
    rno number(10,0),
    bno number(10,0),
    reply nvarchar2(1000) not null,
    replyer nvarchar2(50) not null,
    replyDate date default sysdate,
    updateDate date default sysdate
);



create sequence seq_reply;

alter table tbl_reply add CONSTRAINT pk_reply PRIMARY KEY(rno);

alter table tbl_reply add CONSTRAINT fk_reply_board FOREIGN KEY (bno) REFERENCES tbl_board (bno);