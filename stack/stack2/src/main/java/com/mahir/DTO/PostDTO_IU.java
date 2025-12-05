package com.mahir.DTO;

import lombok.Data;
import lombok.Getter;




@Data
public  class  PostDTO_IU extends SuperMappedDTO{
  private String Title;

   
    private String Text;

    private Long user_id;
}