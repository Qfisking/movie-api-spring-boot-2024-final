SELECT *
FROM staff
WHERE
    /*%if param!=null && @isNotBlank(param.getChineseName())*/
        chinese_name ILIKE '%' || /* param.getChineseName() */'斯' || '%'
    /*%end*/
    /*%if param!=null && @isNotBlank(param.getEnglishName())*/
    AND english_name ILIKE '%' || /* param.getEnglishName() */'lee' || '%'
    /*%end*/
    /*%if param!=null && @isNotBlank(param.getRegion())*/
    AND region ILIKE'%' || /*param.getRegion()*/'美国' || '%'
    /*%end*/
    /*%if param!=null && @isNotBlank(param.getGender())*/
    AND gender ILIKE '%' || /*param.getGender()*/'女' || '%'
    /*%end*/