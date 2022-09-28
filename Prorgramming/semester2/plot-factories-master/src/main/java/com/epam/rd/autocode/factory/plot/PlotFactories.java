package com.epam.rd.autocode.factory.plot;

class PlotFactories {

    public PlotFactory classicDisneyPlotFactory(Character hero, Character beloved, Character villain) {
        return new PlotFactory() {
            @Override
            public Plot plot() {
                final Plot plot = new Plot() {
                    @Override
                    public String asText() {
                        return hero.name() + " is young and adorable. " + hero.name() + " and " + beloved.name() + " love each other. " + villain.name() + " interferes with their happiness, but loses in the end.";
                    }

                };
                return plot;
            }
        };
    }

    public PlotFactory contemporaryDisneyPlotFactory(Character hero, EpicCrisis epicCrisis, Character funnyFriend) {
        return new PlotFactory() {
            @Override
            public Plot plot() {
                final Plot plot2= new Plot(){

                    @Override
                    public String asText() {
                       return "In the beginning, " + hero.name() + " feels a bit awkward and uncomfortable. But personal issue fades, when a big trouble comes - " + epicCrisis.name()+"." + " " + hero.name() + " stands up against it, but with no success at first. But then, by putting self together and with the help of friends, including spectacular, funny " + funnyFriend.name()+", "+ hero.name() + " restores the spirit, overcomes the crisis and gains gratitude and respect.";
                    }
                };
                return plot2;
            }
        };
    }

    public PlotFactory marvelPlotFactory(Character[] heroes, EpicCrisis epicCrisis, Character villain) {
        return new PlotFactory() {
            @Override
            public Plot plot() {
                final Plot plot = new Plot() {
                    @Override
                    public String asText() {
                        String x = "";
                        int heroesLength = heroes.length;

                        //System.out.println(heroesLength);
                        if(heroesLength==1){
                            x+="the brave "+ heroes[0].name();
                        }
                        else{
                            for(int i=0; i<=heroesLength-1;i++){
                                if(i==heroesLength-1){
                                    x+="the brave "+ heroes[i].name();
                                }
                                else if (i == heroes.length-2){
                                    x+="the brave "+ heroes[i].name()+" and ";
                                }
                                else{
                                    x+="the brave "+ heroes[i].name()+", ";
                                }
                            }
                        }
                        String q = "";
                        int o=1;
                           for(int i=0; i<= heroes.length; i++) {
                               if (o==i) {
                                   q = " is";

                               } else {
                                   q = " are";
                               }
                           }
                           String m= "";
                           String n= "";
                           int k=1;
                        for(int i=0; i<= heroes.length; i++){
                            if(k==i){
                                m = "hero.";
                                n = "the ";
                            }
                            else {
                                m = "heroes.";
                                n = "";
                            }
                        }
                            return epicCrisis.name() + " threatens the world. But " + x + q + " on guard. So, no way that intrigues " + "of " + villain.name() + " will bend the willpower of " +n + "inflexible "+ m;

                    }
                };
                return plot;
            }
        };
    }
}
