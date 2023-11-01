package com.green.SecondLife.community.vo;

import lombok.Data;

@Data
public class PageVO {
    private int nowPage; //현재 페이지 번호
    private int totalDataCnt; //전체 데이터 수
    private int displayDataCnt; //한 페이지에 보여지는 게시글 수 예) 한 페이지에 게시글 10개 출력
    private int beginPage; // 첫번째 페이지 번호                예) 1, 6, 11
    private int endPage; // 마지막 페이지 번호                  예) 5, 10, 15
    private int diaplayPageCnt; // 한번에 출력되는 페이지 수     예) 만약 5라면,  이전 1 2 3 4 5 다음
    private boolean prev; // 이전 버튼의 유무
    private boolean next; // 다음 버튼의 유무

    public PageVO(){ //페이징처리 기본값 설정
        nowPage = 1; //현재 페이지 1
        diaplayPageCnt = 5; // 1 2 3 4 5
        displayDataCnt = 10;
    }

    //페이징 처리하기위해 변수값 세팅 + - * /
    public void setPageInfo(){//                                                    0.8
        endPage = (int)Math.ceil(nowPage / (double)diaplayPageCnt) * diaplayPageCnt; //만약 현재 4페이지라면 4/5*5=4
        beginPage = endPage - diaplayPageCnt + 1;//첫번째 페이지 = 5 - 5 + 1;

        //전체 페이지 수
        int totalPageCnt = (int)Math.ceil(totalDataCnt / (double)displayDataCnt);

        //next(다음) 버튼 유무
        if (endPage < totalPageCnt){ //마지막 페이지가 전체 페이지 수보다 작다면
            next = true;
        }
        else {
            next = false;
            if(totalPageCnt == 0){//전체 데이터가 하나도 없다면
                endPage = 1; //화면에 출력되는 페이지를 1로 설정
            }
            else{
                endPage = totalPageCnt; //마지막 페이지랑 전체 페이지랑 맞춤
            }

        }
        //prev(이전) 버튼 유무
        if (beginPage == 1){ //첫번째 페이지가 1과 같다면 이전버튼은 사라져야한다
            prev = false;
        }
        else {
            prev = true;
        }
    }

    //전체 데이터 수 setter
    public void setTotalPageCnt(int totalDataCnt){
        this.totalDataCnt = totalDataCnt;
    }
    // 현재 페이지 getter
    public int getNowPage() {
        return nowPage;
    }
    //현재 페이지 setter
    public void setNowPage(int nowPage) {
        this.nowPage = nowPage;
    }
    //한 페이지에 보여지는 데이터 수 getter
    public int getDisplayDataCnt() {
        return displayDataCnt;
    }
    public int getBeginPage() {
        return beginPage;
    }
    public int getEndPage() {
        return endPage;
    }
    public boolean getPrev() {
        return prev;
    }
    public boolean getNext() {
        return next;
    }

}
