const SYLLABLE = {"C#": "c", "D#": "d", "F#": "f", "G#": "g", "A#": "a"};

const solution = (m, musicinfos) => {
    let result = sortByTime(musicinfos);
    m = changeSyllable(m);
    let filtered = result.filter((value) => value.melody.indexOf(m) !== -1);
    return filtered[0]?.title || "(None)";
}

const sortByTime = (musicInfos) => {
    let result = [];
    musicInfos.forEach((value) => {
        let [start, end, title, melody] = value.split(",");
        let playingTime = getTime(end) - getTime(start);
        let replacedMelody = changeSyllable(melody);
        result.push({title: title,
                     play: playingTime,
                     melody: replacedMelody.repeat(Math.ceil(playingTime / replacedMelody.length)).substr(0, playingTime)});
    });
    return result.sort((a, b) => b.play - a.play);
}

const getTime = (time) => {
    let [h, m] = time.split(":");
    return +h * 60 + +m;
}

const changeSyllable = (melody) => {
    Object.keys(SYLLABLE).forEach((value) => {
        melody = melody.replaceAll(value, SYLLABLE[value]);
    });
    return melody;
}
