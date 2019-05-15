package com.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author CXY
 * @since 2019-04-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TbBook extends Model<TbBook> {

private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    @TableField(exist = false)
    private Long id;

    private String name;

    private Integer bookNum;

    private Integer videoNum;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
