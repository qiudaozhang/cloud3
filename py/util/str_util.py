def first_low(s):
    v1 = s[0:1]
    v2 = s[1:]
    return f"{v1.lower()}{v2}"


def underline_2_words(s):
    words = s.split("_")
    cap_words = map(lambda x: x.capitalize(), words)
    return "".join(cap_words)


def sql_name_2_property(s):
    s1 = underline_2_words(s)
    return s1[:1].lower()+s1[1:]