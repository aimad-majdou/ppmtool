package com.example.ppmtool.core.domain;

import java.io.Serializable;

public interface IHasID<ID extends Serializable> {

    ID getId();
}
