package org.edwith.webbe.cardmanager.dao;

import org.edwith.webbe.cardmanager.dto.BusinessCard;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class BusinessCardManagerDao {
    private static String dburl = "jdbc:mysql://localhost:3306/connectdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static String dbUser = "connectuser";
    private static String dbpasswd = "connect123!@#";


    public List<BusinessCard> searchBusinessCard(String keyword){

        List<BusinessCard> business_cards = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Class.forName( "com.mysql.cj.jdbc.Driver" );

            conn = DriverManager.getConnection ( dburl, dbUser, dbpasswd );

            //이름 검색시 명함정보 보여지기
            String sql = "SELECT name, phone, companyName, createDate as createDate FROM businesscard WHERE name like ? ";

            ps = conn.prepareStatement(sql);
            ps.setString(1,"%"+keyword+"%");
            rs = ps.executeQuery();

            while (rs.next()){
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                String companyName = rs.getString("companyName");
                BusinessCard card = new BusinessCard(name, phone, companyName);
                business_cards.add(card);
            }

        }catch(Exception ex) {
            ex.printStackTrace();
        }finally{
            if (rs!=null){
                try{
                    rs.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (ps!=null){
                try{
                    ps.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (conn!=null){
                try{
                    conn.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }

        return business_cards;
    }

    public BusinessCard addBusinessCard(BusinessCard businessCard){
        BusinessCard card = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sql = "INSERT INTO businesscard (name, phone, companyName) VALUES ( ?, ?, ? )";

        try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, businessCard.getName());
            ps.setString(2, businessCard.getPhone());
            ps.setString(3, businessCard.getCompanyName());

            int result = ps.executeUpdate();


        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return card;
    }
}
