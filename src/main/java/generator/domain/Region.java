package generator.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

/**
* 地区表
* @TableName region
*/
public class Region implements Serializable {

    /**
    * id
    */
    @NotNull(message="[id]不能为空")
    @ApiModelProperty("id")
    private Long id;
    /**
    * 上级id
    */
    @NotNull(message="[上级id]不能为空")
    @ApiModelProperty("上级id")
    private Long parentId;
    /**
    * 名称
    */
    @NotBlank(message="[名称]不能为空")
    @Size(max= 100,message="编码长度不能超过100")
    @ApiModelProperty("名称")
    @Length(max= 100,message="编码长度不能超过100")
    private String name;
    /**
    * 创建时间
    */
    @NotNull(message="[创建时间]不能为空")
    @ApiModelProperty("创建时间")
    private Date createTime;
    /**
    * 更新时间
    */
    @NotNull(message="[更新时间]不能为空")
    @ApiModelProperty("更新时间")
    private Date updateTime;
    /**
    * 删除标记（0:不可用 1:可用）
    */
    @NotNull(message="[删除标记（0:不可用 1:可用）]不能为空")
    @ApiModelProperty("删除标记（0:不可用 1:可用）")
    private Integer isDeleted;

    /**
    * id
    */
    private void setId(Long id){
    this.id = id;
    }

    /**
    * 上级id
    */
    private void setParentId(Long parentId){
    this.parentId = parentId;
    }

    /**
    * 名称
    */
    private void setName(String name){
    this.name = name;
    }

    /**
    * 创建时间
    */
    private void setCreateTime(Date createTime){
    this.createTime = createTime;
    }

    /**
    * 更新时间
    */
    private void setUpdateTime(Date updateTime){
    this.updateTime = updateTime;
    }

    /**
    * 删除标记（0:不可用 1:可用）
    */
    private void setIsDeleted(Integer isDeleted){
    this.isDeleted = isDeleted;
    }


    /**
    * id
    */
    private Long getId(){
    return this.id;
    }

    /**
    * 上级id
    */
    private Long getParentId(){
    return this.parentId;
    }

    /**
    * 名称
    */
    private String getName(){
    return this.name;
    }

    /**
    * 创建时间
    */
    private Date getCreateTime(){
    return this.createTime;
    }

    /**
    * 更新时间
    */
    private Date getUpdateTime(){
    return this.updateTime;
    }

    /**
    * 删除标记（0:不可用 1:可用）
    */
    private Integer getIsDeleted(){
    return this.isDeleted;
    }

}
