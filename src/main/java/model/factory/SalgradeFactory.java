package model.factory;

import model.Salgrade;

public class SalgradeFactory {
    private static SalgradeFactory instance;

    public static SalgradeFactory getInstance() {
        if (instance == null)
            instance = new SalgradeFactory();
        return instance;
    }

    public Salgrade createSalgrade(int grade, int losal, int hisal) {
        return new Salgrade(grade, losal, hisal);
    }
}
