package com.brammulder.quizapp;

/**
 * Created by Bram on 31-5-2015.
 */
public class Users {

    private int _id;
    private String _name;

    public Users() {
    }

    public Users(String name){
        this._name = name;
    }

    public void set_id(int _id){
        this._id = _id;
    }

    public void set_name(String _name){
        this._name = _name;
    }

    public int get_id(){
        return _id;
    }

    public String get_name(){
        return _name;
    }
}