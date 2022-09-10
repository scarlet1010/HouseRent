package com.qh.chanage.houseRent.service;

import com.qh.chanage.houseRent.domain.House;

import java.util.Arrays;

public class HouseService {

    private House[] houses=new House[0];
    public House[] list(){
        return houses;
    }
    //每调用一次添加数组时 扩展数组，再把传入的对象赋给最后一个数组
    int numId=1;
    public boolean add(House h){
        houses= Arrays.copyOf(houses,houses.length+1);
        houses[houses.length-1]=h;
        houses[houses.length-1].setId(numId++);
        return true;
    }
    public boolean del(int delId){
        //标记，是否有能被删除的数
        int mark=-1;
        for (int i = 0; i < houses.length; i++) {
            if(delId==houses[i].getId()){
                mark=i;
            }
        }
        //有，则从标记位置开始后面的数组元素都向前移
        if(mark!=-1){
            for (int j = mark; j < houses.length - 1; j++) {
                houses[j]=houses[j+1];
            }
            //利用缩小数组空间去除最后一个元素
            houses=Arrays.copyOf(houses,houses.length-1);
            return true;
        }else return false;

    }
    public int find(int findId){
        //mark记录下标，未找到为-1
        int mark=-1;
        //循环查找id，用mark储存对应数组下标
        for (int i = 0; i < houses.length; i++) {
            if(findId==houses[i].getId()){
                mark=i;

            }
        }
        return mark;
    }

    //接受修改的数组下标和修改完的数组
    public void fix(int mark,House house){
        houses[mark]=house;
    }
    public House[] getHouses() {
        return houses;
    }

    public void setHouses(House[] houses) {
        this.houses = houses;
    }
}
