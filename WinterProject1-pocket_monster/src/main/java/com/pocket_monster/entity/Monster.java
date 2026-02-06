package com.pocket_monster.entity;

import com.pocket_monster.enumeration.NatureType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Monster {

    /**
     * 小怪兽的名字,年龄,物种
     */
    private int id;//唯一编号
    private MonsterSpecies speices;//种类
    private String nickname;//昵称,注意与种类的名字区分
    private Integer age;//年龄
    private NatureType nature;//性格
    private LocalDateTime birthTime;//出生日期

    private ArrayList<Story> storyList;//故事列表

    public int getStoryCount() {
        return storyList==null?0:storyList.size();
    }

    /**
     * toString重写,方便打印信息
     * @return
     */
    @Override
    public String toString() {
        //日期也要规范
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        //format格式化处理
        return String.format("""
                怪兽信息：
                id：%s
                图鉴信息：
                %s 
                昵称：%s
                年龄：%s
                性格：%s
                出生时间：%s
                故事数量：%s
                故事合集：%s
                """,
                id,
                speices.toString(),
                nickname,
                age,
                nature != null ? nature.getNatureName() : "未知",
                birthTime != null ? birthTime.format(formatter) : "未知",
                getStoryCount(),
                !storyList.isEmpty() ? storyList.toString():"它的故事还没有开始");
    }
}
