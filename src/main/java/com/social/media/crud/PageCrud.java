package com.social.media.crud;

import com.social.media.model.Page;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class PageCrud implements Crud<Page, Integer>{

    @Override
    public Page insert(Connection connection, Page page) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("insert into pages (name, create_date) values (?,?)");
        preparedStatement.setString(1, page.getPageName());
        preparedStatement.setTimestamp(2, page.getCreatePageDate());
        preparedStatement.execute();
        return page;
    }

    @Override
    public Page get(Connection connection, Integer key) throws SQLException {
        return null;
    }

    @Override
    public Page update(Connection connection, Page page) throws SQLException {
        return null;
    }

    @Override
    public int delete(Connection connection, Integer key) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("delete from pages where id  = ?");
        preparedStatement.setInt(1, key);
        int count = preparedStatement.executeUpdate();
        return count;
    }
}
