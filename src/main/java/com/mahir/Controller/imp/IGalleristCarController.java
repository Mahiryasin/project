package com.mahir.Controller.imp;

import java.util.List;

import com.mahir.Entity.GalleristCar;

public interface IGalleristCarController {

    GalleristCar saveGalleristCar(GalleristCar galleristCar);

    List<GalleristCar> getAllGalleristCars();

}


