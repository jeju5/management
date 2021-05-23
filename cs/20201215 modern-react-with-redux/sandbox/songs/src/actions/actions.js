export const selectSong = (songName) => {
    return {
       type: 'SONG_SELECTED',
       payload: songName
    };
};
