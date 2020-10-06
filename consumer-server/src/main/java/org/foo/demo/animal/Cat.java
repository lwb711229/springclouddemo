package org.foo.demo.animal;


import org.foo.demo.IShout;

public class Cat implements IShout {

    public void shout() {
        System.out.println("miao miao");
    }

}