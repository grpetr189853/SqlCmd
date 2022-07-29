package ua.com.juja.sqlcmd.controller;

import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.view.View;

public class MainController {

    private View view;
    private DatabaseManager manager;

    public MainController(View view, DatabaseManager manager){
       this.view = view;
       this.manager = manager;
    }

    public void run(){
        connectToDb();
        //
        //
    }

    private void connectToDb() {
        view.write("Привет юзер!");
        view.write("Введи пожалуйста имя базы данных, пользователя и пароль в формате: database|userName|password");
        while(true) {
            try {
                String string = view.read();
                String[] data = string.split("\\|");
                if (data.length != 3){
                    throw new IllegalAccessException("Неверное количество параметров разделенных знаком '|' ожидается 3, но есть : " + data.length);
                }
                String databaseName = data[0];
                String userName = data[1];
                String password = data[2];
                manager.connect(databaseName, userName, password);
                break;
            } catch (Exception e) {
                printError(e);
            }
        }
        view.write("Подключились");
    }

    private void printError(Exception e) {
        String message = /*e.getClass().getSimpleName() + ": "+*/ e.getMessage();
        Throwable cause = e.getCause();
        if(cause != null){
            message += " " /*+ cause.getClass().getSimpleName()*/ + cause.getMessage();
        }
        view.write("Неудача! по причине: " + message);
        view.write("Повторите попытку!");
    }
}
