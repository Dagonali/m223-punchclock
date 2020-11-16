package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.domain.Entry;
import ch.zli.m223.punchclock.service.EntryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * The Entry endpoint.
 * GET: {URL}/entries - To see all entries
 * DELETE: {URL}/entries/{id} - To delete a entry by the entry-id
 * PUT: {URL}/entries/{id} - To update a entry by the entry-id
 */
@RestController
@RequestMapping("/entries")
public class EntryController {
    private EntryService entryService;

    public EntryController(EntryService entryService) {
        this.entryService = entryService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Entry> getAllEntries() {
        return entryService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Entry createEntry(@Valid @RequestBody Entry entry) {
        return entryService.createEntry(entry);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteEntry(@PathVariable Long id){
        entryService.deleteEntry(id);
    }

    @PutMapping(value = "/{id}")
    public Entry updateEntry(@RequestBody Entry entry, @PathVariable Long id){
        entry.setId(id);
        return entryService.updateEntry(entry);
    }

}
