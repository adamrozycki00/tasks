package com.crud.tasks.domain;

import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class TrelloMapper {

    public TrelloBoard mapToBoard(TrelloBoardDTO dto) {
        return new TrelloBoard(dto.getId(), dto.getName(), mapToLists(dto.getLists()));
    }

    public TrelloBoardDTO mapToBoardDTO(TrelloBoard board) {
        return new TrelloBoardDTO(board.getId(), board.getName(), mapToListDTOs(board.getLists()));
    }

    public TrelloList mapToList(TrelloListDTO dto) {
        return new TrelloList(dto.getId(), dto.getName(), dto.isClosed());
    }

    public TrelloListDTO mapToListDTO(TrelloList list) {
        return new TrelloListDTO(list.getId(), list.getName(), list.isClosed());
    }

    public TrelloCard mapToCard(TrelloCardDTO dto) {
        return new TrelloCard(dto.getName(), dto.getDescription(), dto.getPos(), dto.getListId());
    }

    public TrelloCardDTO mapToCardDTO(TrelloCard card) {
        return new TrelloCardDTO(card.getName(), card.getDescription(), card.getPos(), card.getListId());
    }

    public List<TrelloBoard> mapToBoards(List<TrelloBoardDTO> boardDTOs) {
        return boardDTOs.stream()
                .map(this::mapToBoard)
                .collect(toList());
    }

    public List<TrelloBoardDTO> mapToBoardDTOs(List<TrelloBoard> boards) {
        return boards.stream()
                .map(this::mapToBoardDTO)
                .collect(toList());
    }

    public List<TrelloList> mapToLists(List<TrelloListDTO> dtoList) {
        return dtoList.stream()
                .map(this::mapToList)
                .collect(toList());
    }

    public List<TrelloListDTO> mapToListDTOs(List<TrelloList> lists) {
        return lists.stream()
                .map(this::mapToListDTO)
                .collect(toList());
    }

}
