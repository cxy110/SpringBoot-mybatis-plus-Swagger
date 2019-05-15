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
public class IndexBook extends Model<IndexBook> {

private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

    private String level;

    private Integer time;

    private String bookImg;

    private String direction;

    private String teacher;

    private Integer price;

    private Integer studentNum;

    private Integer rate;

    private Integer knowledgePointNum;

    /**
     * 1 入门课程 2最新课程 3免费课程 推荐课程
     */
    private Integer status;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
