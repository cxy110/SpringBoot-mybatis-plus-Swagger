package com.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
public class StudentSection extends Model<StudentSection> {

private static final long serialVersionUID=1L;

    @TableId(value = "section_id", type = IdType.AUTO)
    private Integer sectionId;

    private Integer id;

    private String name;

    private String duration;

    private String isFree;

    private Integer bookId;


    @Override
    protected Serializable pkVal() {
        return this.sectionId;
    }

}
