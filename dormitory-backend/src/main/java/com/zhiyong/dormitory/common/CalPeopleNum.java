package com.zhiyong.dormitory.common;

import com.zhiyong.dormitory.entity.DormRoom;

public class CalPeopleNum {

    public static int calNum(DormRoom dormRoom) {
        int count = 0;

        if (dormRoom.getFirstBed() != null) {
            count++;
        }
        if (dormRoom.getSecondBed() != null) {
            count++;
        }
        if (dormRoom.getThirdBed() != null) {
            count++;
        }
        if (dormRoom.getFourthBed() != null) {
            count++;
        }
        return count;
    }
}
