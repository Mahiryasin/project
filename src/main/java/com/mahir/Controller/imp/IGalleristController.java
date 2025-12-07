package com.mahir.Controller.imp;


import java.util.List;

import com.mahir.Entity.Gallerist;

public interface IGalleristController {
    Gallerist saveGallerist(Gallerist gallerist);
    List<Gallerist> getAllGallerists();
}