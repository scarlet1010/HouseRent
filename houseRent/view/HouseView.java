package com.qh.chanage.houseRent.view;

import com.qh.chanage.houseRent.domain.House;
import com.qh.chanage.houseRent.service.HouseService;
import com.qh.chanage.houseRent.utility.Utility;

import java.util.Scanner;

import static com.qh.chanage.houseRent.utility.Utility.readConfirmSelection;
import static com.qh.chanage.houseRent.utility.Utility.readMenuSelection;

public class HouseView {

    private char choice;
    private boolean tap=true;
    Scanner useIn = new Scanner(System.in);
    private HouseService houseService=new HouseService();
    public void listHouse(){
        System.out.println("============房屋列表===========");
        System.out.println("编号\t\t房主\t\t电话\t\t地址\t\t月租\t\t状态(未出租/已出租)");
        House[] houses=houseService.list();
        for (House arr :
                houses) {
            System.out.println(arr);
        }
    }
    public void addHouse(){
        System.out.println("请输入房主,电话,地址,月租,状态(未出租/已出租)");
        House house=new House(useIn.next(),useIn.nextInt(),useIn.next(),useIn.nextInt(),useIn.next());
        houseService.add(house);//add返回bool
    }
    public void delHouse(){
        System.out.println("请输入待删除房屋的编号(-1退出)");
        int delId= Utility.readInt();
        if(delId==-1){
            System.out.println("放弃删除房屋");
            return;
        }
        if(readConfirmSelection()=='Y'){
            if(houseService.del(delId)==true){
                System.out.println("删除成功");
            }else{
                System.out.println("删除失败");
            }
        }else{
            System.out.println("取消删除");

        }
    }
    public void findHouse(){
        System.out.println("请输入要查找房屋的ID(-1退出");
        int findId=Utility.readInt();
        if(findId==-1){
            System.out.println("退出查找房屋");
            return;
        }
        //mark数组记录下标，未找到为-1
        int mark=houseService.find(findId);
        if(mark==-1){
            System.out.println("未查找到对应的房屋");
        }else{
            System.out.println("编号\t\t房主\t\t电话\t\t地址\t\t月租\t\t状态(未出租/已出租)");
            System.out.println(houseService.getHouses()[mark]);
        }

    }
    public void fixHouse(){
        System.out.println("请输入要修改的房屋编号");
        int fixId=Utility.readInt();
        int mark=houseService.find(fixId);
        if(mark==-1){
            System.out.println("没有对应的房屋可修改");
        }else{
        System.out.print("房主：");
        String name=Utility.readString(10,houseService.getHouses()[mark].getName());
        System.out.print("电话：");
        int phone=Utility.readInt(houseService.getHouses()[mark].getPhone());
        System.out.print("地址：");
        String address=Utility.readString(10,houseService.getHouses()[mark].getAddress());
        System.out.print("月租：");
        int rent=Utility.readInt(houseService.getHouses()[mark].getRent());
        System.out.print("状态：");
        String state=Utility.readString(10,houseService.getHouses()[mark].getState());
        House fixHouses = new House(name,phone,address,rent,state);
        fixHouses.setId(houseService.getHouses()[mark].getId());
        houseService.fix(mark,fixHouses);
    }
    }
    public void mainMenu(){

        do{
            System.out.println("-------------房屋出租系统-------------"+
                            "\n\t\t\t1 新 增 房 源"+
                            "\n\t\t\t2 查 找 房 屋"+
                            "\n\t\t\t3 删 除 房 屋"+
                            "\n\t\t\t4 修 改 房 屋 信 息"+
                            "\n\t\t\t5 房 屋 列 表"+
                            "\n\t\t\t6 退       出");
            choice=readMenuSelection();

            switch(choice){
                case '1':
                    addHouse();
                    break;
                case '2':
                    findHouse();
                    break;
                case '3':
                    delHouse();
                    break;
                case '4':
                    fixHouse();
                    break;
                case '5':
                    listHouse();
                    break;
                case '6':
                    if(readConfirmSelection()=='Y'){
                        tap=false;
                    }
                    break;
            }
        }while (tap);
    }



}
