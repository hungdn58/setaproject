<nav class="large-3 medium-4 columns" id="actions-sidebar">
    <ul class="side-nav">
        <li class="heading"><?= __('Actions') ?></li>
        <li><?= $this->Html->link(__('Edit Footprint'), ['action' => 'edit', $footprint->id]) ?> </li>
        <li><?= $this->Form->postLink(__('Delete Footprint'), ['action' => 'delete', $footprint->id], ['confirm' => __('Are you sure you want to delete # {0}?', $footprint->id)]) ?> </li>
        <li><?= $this->Html->link(__('List Footprints'), ['action' => 'index']) ?> </li>
        <li><?= $this->Html->link(__('New Footprint'), ['action' => 'add']) ?> </li>
    </ul>
</nav>
<div class="footprints view large-9 medium-8 columns content">
    <h3><?= h($footprint->id) ?></h3>
    <table class="vertical-table">
        <tr>
            <th><?= __('Id') ?></th>
            <td><?= $this->Number->format($footprint->id) ?></td>
        </tr>
        <tr>
            <th><?= __('Visitor') ?></th>
            <td><?= $this->Number->format($footprint->visitor) ?></td>
        </tr>
        <tr>
            <th><?= __('DelFlg') ?></th>
            <td><?= $this->Number->format($footprint->delFlg) ?></td>
        </tr>
        <tr>
            <th><?= __('CreateDate') ?></th>
            <td><?= h($footprint->createDate) ?></td>
        </tr>
    </table>
</div>
