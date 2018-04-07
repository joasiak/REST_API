package com.crud.tasks.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class TrelloMapperTest {

    //@Autowired
     private TrelloMapper trelloMapper;

    @Before
    public void setUp() throws Exception {
        trelloMapper = new TrelloMapper();
    }

    @Test
    public void mapToBoards() throws Exception {
        //Given
        List<TrelloListDto> givenTrelloListDto = new ArrayList<>();
        givenTrelloListDto.add(new TrelloListDto("1", "test_list", false));

        List<TrelloBoardDto> givenTrelloBoardsDto = new ArrayList<>();
        givenTrelloBoardsDto.add(new TrelloBoardDto("1", "test", givenTrelloListDto));

        List<TrelloList> expectedTrelloLists = new ArrayList<>();
        expectedTrelloLists.add(new TrelloList("1", "test_list", false));

        List<TrelloBoard> expectedTrelloBoards = new ArrayList<>();
        expectedTrelloBoards.add(new TrelloBoard("1", "test", expectedTrelloLists));

        //When
        List<TrelloBoard> mappedTrelloBoard = trelloMapper.mapToBoards(givenTrelloBoardsDto);

        //Then
        Assert.assertEquals(mappedTrelloBoard.size(), expectedTrelloBoards.size());

    }

    @Test
    public void mapToBoardsDto() throws Exception {

        //Given
        List<TrelloListDto> exteptedTrelloListDto = new ArrayList<>();
        exteptedTrelloListDto.add(new TrelloListDto("1", "test_list", false));

        List<TrelloBoardDto> expectedTrelloBoardsDto = new ArrayList<>();
        expectedTrelloBoardsDto.add(new TrelloBoardDto("1", "test", exteptedTrelloListDto));

        List<TrelloList> givenTrelloLists = new ArrayList<>();
        givenTrelloLists.add(new TrelloList("1", "test_list", false));

        List<TrelloBoard> givenTrelloBoards = new ArrayList<>();
        givenTrelloBoards.add(new TrelloBoard("1", "test", givenTrelloLists));

        //When

        List<TrelloBoardDto> mappedTrelloBoardDto = trelloMapper.mapToBoardsDto(givenTrelloBoards);

        //Then

        Assert.assertEquals(mappedTrelloBoardDto.size(), givenTrelloBoards.size());

    }

    @Test
    public void mapToList() throws Exception {

        //Given
        List<TrelloListDto> givenTrelloListDto = new ArrayList<>();
        givenTrelloListDto.add(new TrelloListDto("1", "test_list", false));

        List<TrelloList> expectedTrelloLists = new ArrayList<>();
        expectedTrelloLists.add(new TrelloList("1", "test_list", false));

        //When
        List<TrelloList> mappedTrelloList = trelloMapper.mapToList(givenTrelloListDto);

        //Then
        Assert.assertEquals(mappedTrelloList.size(), expectedTrelloLists.size());

    }

    @Test
    public void mapToListDto() throws Exception {

        //Given
        List<TrelloListDto> expectedTrelloListDto = new ArrayList<>();
        expectedTrelloListDto.add(new TrelloListDto("1", "test_list", false));

        List<TrelloList> givenTrelloLists = new ArrayList<>();
        givenTrelloLists.add(new TrelloList("1", "test_list", false));


        //When
        List<TrelloListDto> mappedTrelloListDto = trelloMapper.mapToListDto(givenTrelloLists);

        //Then

        Assert.assertEquals(mappedTrelloListDto.size(), expectedTrelloListDto.size());
        Assert.assertEquals(mappedTrelloListDto.get(0).getName(), expectedTrelloListDto.get(0).getName());
    }

    @Test
    public void mapToCardDto() throws Exception {

        TrelloCardDto expectedTrelloCardDto = new TrelloCardDto("Card1", "Desc1", "Pos1", "List1");
        TrelloCard givenTrelloCard = new TrelloCard("Card1", "Desc1", "Pos1", "List1");

        TrelloCardDto mappedTrelloCardDto = trelloMapper.mapToCardDto(givenTrelloCard);

        Assert.assertEquals(expectedTrelloCardDto.getName(), mappedTrelloCardDto.getName());
        Assert.assertEquals(expectedTrelloCardDto.getListId(), mappedTrelloCardDto.getListId());

        Assert.assertEquals(expectedTrelloCardDto.getPos(), mappedTrelloCardDto.getPos());
        Assert.assertEquals(expectedTrelloCardDto.getDescription(), mappedTrelloCardDto.getDescription());
    }

    @Test
    public void mapToCard() throws Exception {

        TrelloCardDto givenTrelloCardDto = new TrelloCardDto("Card1", "Desc1", "Pos1", "List1");
        TrelloCard expectedTrelloCard = new TrelloCard("Card1", "Desc1", "Pos1", "List1");

        TrelloCard mappedTrelloCard = trelloMapper.mapToCard(givenTrelloCardDto);

        Assert.assertEquals(expectedTrelloCard.getName(), mappedTrelloCard.getName());
        Assert.assertEquals(expectedTrelloCard.getListId(), mappedTrelloCard.getListId());

        Assert.assertEquals(expectedTrelloCard.getPos(), mappedTrelloCard.getPos());
        Assert.assertEquals(expectedTrelloCard.getDescription(), mappedTrelloCard.getDescription());
    }


}