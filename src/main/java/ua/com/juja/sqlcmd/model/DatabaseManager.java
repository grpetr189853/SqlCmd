package ua.com.juja.sqlcmd.model;

public interface DatabaseManager {

    void connect(String database, String userName, String password);

    void clear(String tableName);

    void create(String tableName, DataSet input);

    void update(String tableName, int id, DataSet newValue);

    String[] getTableNames();

    DataSet[] getTableData(String tableName);
}
