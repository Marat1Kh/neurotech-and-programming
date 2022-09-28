package com.epam.rd.autocode.startegy.cards;

import com.epam.rd.autocode.Named;

public interface Card extends Named {
    public class card implements Card {
        String name;

        public card(String name) {
            this.name = name;
        }

        @Override
        public String name() {
            return name;
        }

        @Override
        public String toString() {
            return name;
        }


    }
}
