package HuHelper;

public class HuType {
/*
    */
/**
     * 查找去除将牌后的断点（没有连续的牌就是一个断点）
     *//*

    get_breaks(card, card_length)
    {
        var breaks = [];
        var count = 1;
        breaks[0] = 0;

        for (var i = 1; i < card_length; i++)
        {
            if ((card[i].value - card[i - 1].value) > 1 && i < card_length-1)
            {
                breaks[count] = i;
                count += 1;
            }
            else if(i == card_length-1) //最后一次
            {
                if ( (card[i+1].value - card[i].value) > 1 ) {
                    breaks[count] = i+1;
                    count += 1;
                }

                if((card[i].value - card[i-1].value) > 1){
                    breaks[count] = i;
                    count += 1;
                }
            }
        }

        breaks[count] = card_length+1;
        var breakss = [];
        breakss[0] = 0;

        for (var i = 0; i < count + 1; i++)
        {
            breakss[i] = breaks[i];
        }

        return breakss;
    }
    */
/**
     * 根据手牌和断点的数组 返回经过处理的牌型
     *//*

    all_parts(card, breaks)
    {
        var partnum = breaks.length - 1;  //断点个数
        var parts = [];
        var arr = [];

        for (var i = 0; i < partnum; i++)
        {
            for (var j = 0; j < breaks[i + 1] - breaks[i]; j++)
            {
                j == 0 ? arr = [] : null;
                arr[j] = card[breaks[i] + j];
            }

            parts[i] = this.postion_translate(arr, arr.length);
        }

        return parts;
    }


    */
/**
     * 拆解位转换
     * （五万、五万、五万、六万、七万、七万、八万、八万、九万）=》（3 1 2 2 1）
     *//*

    postion_translate(arr,arr_length)
    {
        var count = 1;
        var split = [];
        var status = 0; //0状态下为单数状态，1状态下为计数状态

        for(var i = 0; i < arr_length-1;)
        {
            var index_value = arr[i].value;

            if (index_value == arr[i+1].value)
            {
                status = 1;
                i++;
                count += 1;
            }
            else
            {
                status == 1 ? split.push(count) : count = 1 ;
                i++;
                count = 1;
                status == 0 ? split.push(count) : count = 1;
                status = 0;
            }

            i == arr_length - 1 ? split.push(count) : count = count;
        }

        return split;
    }
*/

}
