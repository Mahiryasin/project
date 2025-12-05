package com.mahir.Service.imp;

import java.util.List;

import com.mahir.Entity.GalleristCar;

public interface IGalleristCarService {
  GalleristCar saveGalleristCar(GalleristCar galleristCar);

    // Tüm kayıtları çekme
    List<GalleristCar> getAllGalleristCars();
}
