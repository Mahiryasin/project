package com.mahir.Controller.imp;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import com.mahir.DTO.GalleristCarDetailsDTO;
import com.mahir.DTO.GalleristCarRequest;
import com.mahir.Entity.GalleristCar;

public interface IGalleristCarController {

    public GalleristCar saveGalleristCar(GalleristCarRequest galleristCarRequest);

    List<GalleristCar> getAllGalleristCars();
public List<GalleristCarDetailsDTO> getMySalableCars(@PathVariable int userId) ;
public int getSalableCarCountByUserId(int userId) ;
 public List<com.mahir.DTO.GalleristCarDetailsDTO> getNewestCarsByUserId(int userId) ;
           public List<com.mahir.DTO.GalleristCarDetailsDTO> getOldestCarsByUserId(int userId) ;
    public void deleteCarById(int carId);

     public List<com.mahir.DTO.GalleristCarDetailsDTO> getSoldCarsByUserId(int userId);


int getSoldCarCountByUserId(int userId);

List<GalleristCarDetailsDTO> getNewestSoldCarsByUserId(int userId);

List<GalleristCarDetailsDTO> getOldestSoldCarsByUserId(int userId);
public java.math.BigDecimal getTotalEarningsByUserIdAndCurrency(int userId, String currencyCode);



}


