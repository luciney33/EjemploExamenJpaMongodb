package org.example.examenjpamongodbexample.domain.error;

import org.example.common.Constantes;

public class FOREING_KEY_ERROR extends DatabaseError {
    public FOREING_KEY_ERROR() {
        super(Constantes.FOREING_KEY_ERROR);
    }

}
