<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page import="com.closetou.member.model.service.MemberService" %>
<%@page import="com.closetou.member.model.vo.Member"%>
<%@page import="com.closetou.member.model.dao.MemberDao"%>

<!DOCTYPE html>
<html>
<head>
    <title>Member List</title>
</head>
<body>
    <table border="1">
        <tr>
            <th>No</th>
            <th>User ID</th>
            <th>User Name</th>
            <th>Nickname</th>
            <th>Phone</th>
            <th>Email</th>
            <th>Address</th>
            <th>Address Detail</th>
            <th>Status</th>
            <th>Enroll Date</th>
        </tr>

        <c:forEach var="member" items="${members}">
            <tr>
                <td>${member.no}</td>
                <td>${member.user_id}</td>
                <td>${member.user_name}</td>
                <td>${member.nickname}</td>
                <td>${member.phone}</td>
                <td>${member.email}</td>
                <td>${member.address}</td>
                <td>${member.address_detail}</td>
                <td>${member.status}</td>
                <td>${member.enroll_date}</td>
            </tr>
        </c:forEach>
    </table>
    
    <%
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "CLOSETOU", "CLOSETOU");

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM MEMBER");

            ArrayList<Member> memberList = new ArrayList<Member>();

            while (rs.next()) {
                Member member = new Member();
                member.setNo(rs.getInt("NO"));
//                 member.setUserId(rs.getString("USER_ID"));
                member.setPassword(rs.getString("PASSWORD"));
                member.setRole(rs.getString("ROLE"));
//                 member.setUserName(rs.getString("USER_NAME"));
                member.setNickname(rs.getString("NICKNAME"));
                member.setPhone(rs.getString("PHONE"));
                member.setEmail(rs.getString("EMAIL"));
                member.setAddress(rs.getString("ADDRESS"));
//                 member.setAddressDetail(rs.getString("ADDRESS_DETAIL"));
                member.setStatus(rs.getString("STATUS"));
                member.setEnrollDate(rs.getDate("ENROLL_DATE"));
                memberList.add(member);
            }

            request.setAttribute("members", memberList);

            rs.close();
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    %>
</body>
</html>