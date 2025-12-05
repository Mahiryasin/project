package com.mahir.Service.imp;

import java.util.List;
import com.mahir.Entity.Gallerist;

public interface IGalleristService {
    Gallerist saveGallerist(Gallerist gallerist);
    List<Gallerist> getAllGallerists();
}