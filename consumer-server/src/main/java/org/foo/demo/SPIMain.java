package org.foo.demo;

import java.util.Iterator;
import java.util.ServiceLoader;

public class SPIMain {
    public static void main(String[] args) {
        ServiceLoader<IShout> shouts = ServiceLoader.load(IShout.class);

       /* Iterator<IShout> iterator = shouts.iterator();
        while (iterator.hasNext()) {
            IShout iShout = iterator.next();
            iShout.shout();
        };*/

        for (IShout s : shouts) {
            s.shout();
        }
    }

}
