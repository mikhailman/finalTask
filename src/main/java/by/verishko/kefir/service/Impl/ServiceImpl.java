package by.verishko.kefir.service.Impl;

import by.verishko.kefir.dao.Transaction;
import by.verishko.kefir.service.Service;

public abstract class ServiceImpl implements Service {
    public Transaction transaction = null;

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
