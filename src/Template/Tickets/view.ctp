<nav class="large-3 medium-4 columns" id="actions-sidebar">
    <ul class="side-nav">
        <li class="heading"><?= __('Actions') ?></li>
        <li><?= $this->Html->link(__('Edit Ticket'), ['action' => 'edit', $ticket->id]) ?> </li>
        <li><?= $this->Form->postLink(__('Delete Ticket'), ['action' => 'delete', $ticket->id], ['confirm' => __('Are you sure you want to delete # {0}?', $ticket->id)]) ?> </li>
        <li><?= $this->Html->link(__('List Tickets'), ['action' => 'index']) ?> </li>
        <li><?= $this->Html->link(__('New Ticket'), ['action' => 'add']) ?> </li>
        <li><?= $this->Html->link(__('List Destinations'), ['controller' => 'Destinations', 'action' => 'index']) ?> </li>
        <li><?= $this->Html->link(__('New Destination'), ['controller' => 'Destinations', 'action' => 'add']) ?> </li>
    </ul>
</nav>
<div class="tickets view large-9 medium-8 columns content">
    <h3><?= h($ticket->id) ?></h3>
    <table class="vertical-table">
        <tr>
            <th scope="row"><?= __('Destination') ?></th>
            <td><?= $ticket->has('destination') ? $this->Html->link($ticket->destination->name, ['controller' => 'Destinations', 'action' => 'view', $ticket->destination->id]) : '' ?></td>
        </tr>
        <tr>
            <th scope="row"><?= __('Id') ?></th>
            <td><?= $this->Number->format($ticket->id) ?></td>
        </tr>
        <tr>
            <th scope="row"><?= __('Sold') ?></th>
            <td><?= $this->Ticket->sold($ticket->sold) ?></td>
        </tr>
        <tr>
            <th scope="row"><?= __('Created') ?></th>
            <td><?= h($ticket->created) ?></td>
        </tr>
        <tr>
            <th scope="row"><?= __('Validity') ?></th>
            <td><?= h($ticket->validity) ?></td>
        </tr>
    </table>
    <div class="row">
        <h4><?= __('Serial Number') ?></h4>
        <?= $this->Text->autoParagraph(h($ticket->serial_number)); ?>
    </div>
</div>
