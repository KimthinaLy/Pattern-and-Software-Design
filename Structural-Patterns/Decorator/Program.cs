// See https://aka.ms/new-console-template for more information
using System;
using System.Collections.Generic;

interface IMusic
{
  string GetDetail();
  int[] CountType( string[] genre);
}

abstract class MusicGenre(string name, string author) : IMusic
{
  private readonly string songName = name;
  private readonly string author = author;
  public string GetSongName() => songName;
  public string GetAuthor() => author;
  public abstract string GetDetail();
  public abstract int[] CountType( string[] genre);
}

class Hiphop(string name, string author, bool rappingLyrics) : MusicGenre(name, author)
{
  private readonly bool rappingLyrics = rappingLyrics;
  public override string GetDetail() => $"  Genre: hiphop, Song-Name: {GetSongName()}, Author: {GetAuthor()}" +  $", {(rappingLyrics? "has rappingLyrics": "does not have rappingLyrics")}\n";
  public override int[] CountType(string[] genre)
  {
    int length = genre.Length;
    int[] countHiphop = new int[genre.Length];
    for (int i = 0; i < length; i++)
    {
      if (genre[i] == "hiphop")
      {
        countHiphop[i]++;
        break;
      }
    }
    return countHiphop;
  }
}
class ClassicalMusic(string name, string author, string historicalPeriod) : MusicGenre(name, author)
{
  private readonly string historicalPeriod = historicalPeriod;
  public override string GetDetail() => $"  Genre: classical, Song-Name: {GetSongName()}, Author: {GetAuthor()}, Historical-Period: {historicalPeriod}\n";
  public override int[] CountType(string[] genre)
  {
    int length = genre.Length;
    int[] countClassical = new int[genre.Length];
    for (int i = 0; i < length; i++)
    {
      if (genre[i] == "classical")
      {
        countClassical[i]++;
        break;
      }
    }
    return countClassical;
  }
}

class RockMusic(string name, string author, string subGenre) : MusicGenre(name, author)
{
  private readonly string subGenre = subGenre;

  public override string GetDetail() => $"  Genre: rock, subGenre: {subGenre}, Song-Name: {GetSongName()}, Author: {GetAuthor()}\n";
  public override int[] CountType(string[] genre)
  {
    int length = genre.Length;
    int[] countRock = new int[genre.Length];
    for (int i = 0; i < length; i++)
    {
      if (genre[i] == "rock")
      {
        countRock[i]++;
        break;
      }
    }
    return countRock;
  }
}

class MusicPlaylist(string albumName) : IMusic
{
  private readonly List<IMusic> musics = new();
  private readonly string albumName = albumName;

  public void Add(IMusic music)
  {
    musics.Add(music);
  }
  public string GetDetail()
  {
    string detail = "";
    foreach (var music in musics)
    {
      detail += music.GetDetail();
    }
    return $"{albumName}:\n{detail}";
  }
  public int[] CountType(string[] genre)
  {
    int[] totalCount = new int[genre.Length];
    foreach (var music in musics)
    {
      int[] countEachGenre = music.CountType(genre);
      for (int i = 0; i < genre.Length; i++)
      {
        totalCount[i] += countEachGenre[i];
      }
    }
    return totalCount;
  }
}

class Program
{
  public static void ShowPlayList(IMusic musicPlaylist)
  {
    Console.WriteLine(musicPlaylist.GetDetail());
  }
  public static void ShowGenreCounts(IMusic musicPlaylist,string[] genre)
  {
    Console.WriteLine("The number of songs in each genre:");
    int[] count = musicPlaylist.CountType(genre);
    int length = genre.Length;
    for (int i = 0; i < length; i++)
      Console.WriteLine($"{genre[i]} genre: {count[i]} songs");
  }
  static void Main(string[] args)
  {
    string[] genres = [ "hiphop", "classical", "rock" ];
    
    //create main Music-Playlist
    MusicPlaylist mainPlaylist = new("Music Playlist");

    //create Hiphop-Album
    MusicPlaylist hiphopAlbum = new("*Hip-hop Album");
    IMusic hiphop1 = new Hiphop("Hip-HopSong1", "D. William", true);
    IMusic hiphop2 = new Hiphop("Hip-HopSong2", "D. William", false);
    hiphopAlbum.Add(hiphop1);
    hiphopAlbum.Add(hiphop2);
    //add Hiphop-Album to main playlist
    mainPlaylist.Add(hiphopAlbum);

    //create Classical-Music-Album
    MusicPlaylist classicalAlbum = new("*Classical Music Album");
    IMusic classical1 = new ClassicalMusic("ClassicalSong1", "C. Sanji","Baroque");
    IMusic classical2 = new ClassicalMusic("ClassicalSong2", "Vivi J.", "Modern");
    classicalAlbum.Add(classical1);
    classicalAlbum.Add(classical2);
    //add Classical-Music-Album to Main-Playlist
    mainPlaylist.Add(classicalAlbum);

    MusicPlaylist rockAlbum = new("*Rock Album");
    IMusic rock1 = new RockMusic("RockSong1", "Franky Xx.", "metal");
    IMusic rock2 = new RockMusic("RockSong2", "Alexa Xx.", "progressive");
    MusicPlaylist southernRockAlbum = new("**Southern Rock Album");
    IMusic southernRock1 = new RockMusic("RockSong1", "Franky Xx.", "southern");
    IMusic southernRock2 = new RockMusic("RockSong2", "Alexa Xx.", "southern");
    southernRockAlbum.Add(southernRock1);
    southernRockAlbum.Add(southernRock2);
    rockAlbum.Add(rock1);
    rockAlbum.Add(rock2);
    rockAlbum.Add(southernRockAlbum);
    //add Rock-Genres-Album to Main-Playlist
    mainPlaylist.Add(rockAlbum);


    Console.WriteLine("----------Show Main Playlist:----------");
    ShowPlayList(mainPlaylist);

    Console.WriteLine("\n----------Show Number Of Song in Each Genre-----");
    ShowGenreCounts(mainPlaylist,genres);
  }
}
