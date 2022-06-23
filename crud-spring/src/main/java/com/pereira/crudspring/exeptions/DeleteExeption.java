package com.pereira.crudspring.exeptions;

public class DeleteExeption extends RuntimeException {
    
    private static final long serialVersionUID = 1L;

    public DeleteExeption(String msg) {
        super(msg);
    }
}
