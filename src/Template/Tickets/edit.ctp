<nav class="large-3 medium-4 columns" id="actions-sidebar">
    <ul class="side-nav">
        <li class="heading"><?= __('Actions') ?></li>
        <li><?= $this->Form->postLink(
                __('Delete'),
                ['action' => 'delete', $ticket->id],
                ['confirm' => __('Are you sure you want to delete # {0}?', $ticket->id)]
            )
        ?></li>
        <li><?= $this->Html->link(__('List Tickets'), ['action' => 'index']) ?></li>
        <li><?= $this->Html->link(__('List Destinations'), ['controller' => 'Destinations', 'action' => 'index']) ?></li>
        <li><?= $this->Html->link(__('New Destination'), ['controller' => 'Destinations', 'action' => 'add']) ?></li>
    </ul>
</nav>
<div class="tickets form large-9 medium-8 columns content">
    <?= $this->Form->create($ticket) ?>
    <fieldset>
        <legend><?= __('Edit Ticket') ?></legend>
        <?php
            echo $this->Form->input('destination_id', ['options' => $destinations]);
            echo $this->Form->input('serial_number');
            echo $this->Form->input('sold',['type' => 'checkbox']);
            echo $this->Form->input('validity', ['empty' => true]);
        ?>
    </fieldset>
    <?= $this->Form->button(__('Submit')) ?>
    <?= $this->Form->end() ?>
</div>
