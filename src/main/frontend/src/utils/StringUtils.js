export const getFieldName = (name) => {
    const arr = name.replaceAll("]", "").replaceAll("[", ".").split(".");
    return arr;
}