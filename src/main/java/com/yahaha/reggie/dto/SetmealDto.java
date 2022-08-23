package com.yahaha.reggie.dto;

import com.yahaha.reggie.entity.Setmeal;
import com.yahaha.reggie.entity.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
