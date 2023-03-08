def first_low(s):
    v1 = s[0:1]
    v2 = s[1:]
    return f"{v1.lower()}{v2}"


def underline_2_words(s):
    words = s.split("_")
    cap_words = map(lambda x: x.capitalize(), words)
    return "".join(cap_words)
