function solution(skill, skill_trees) {
    const regexp = new RegExp(`[^${skill}]`, "g");
    return skill_trees.reduce((result, current) => {
        current = current.replaceAll(regexp, "")
        result += skill.indexOf(current) === 0 ? 1 : 0;
        return result;
    }, 0);
}
